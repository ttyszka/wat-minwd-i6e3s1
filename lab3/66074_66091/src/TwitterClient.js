const client_secret = require('./config/client_secret.json');
const Fetch = require('cross-fetch');
const querystring = require('querystring');
const createOauthClient = require('./OAuthClient.js');
const getUrl = require('./OAuthURLEncoder.js')
module.exports = class Twitter {
    constructor(options, restHeaders) {
      const config = Object.assign({}, client_secret.defaults, options);
      this.authType = config.authType,
      this.client = createOauthClient({
        key: config.consumer_key,
        secret: config.consumer_secret,
      });
  
      this.token = {
        key: config.access_token_key,
        secret: config.access_token_secret,
      };
  
      this.url = getUrl(config.subdomain);
      this.oauth = getUrl(config.subdomain, 'oauth');
      this.config = config;
      this.baseHeaders = {
        'Content-Type': 'application/json',
        Accept: 'application/json',
      }
    }
    //parsuje JSONa i dodaje _header, twitter przekazuje tam np. informacje o limicie API itd
    static _handleResponse(response) {
      const headers = response.headers.raw(); 
      // 204 = pusty response, same headery
      if (response.status === 204)
        return {
          _headers: headers,
        };
      // Nie pusty -> parsuj
      return response.json().then(res => {
        res._headers = headers; 
        return res;
      });
    }
  
    async getRequestToken(twitterCallbackUrl) {
      const requestData = {
        url: `${this.oauth}/request_token`,
        method: 'POST',
      };
  
      let parameters = {};
      if (twitterCallbackUrl) parameters = { oauth_callback: twitterCallbackUrl };
      if (parameters) requestData.url += '?' + querystring.stringify(parameters);
  
      const headers = this.client.toHeader(
        this.client.authorize(requestData, {})
      );
  
      const results = await Fetch(requestData.url, {
        method: 'POST',
        headers: Object.assign({}, baseHeaders, headers),
      })
        .then(res => res.text())
        .then(txt => querystring.parse(txt));
  
      return results;
    }
  
    async getAccessToken(options) {
      const requestData = {
        url: `${this.oauth}/access_token`,
        method: 'POST',
      };
  
      let parameters = { oauth_verifier: options.verifier };
      if (parameters) requestData.url += '?' + querystring.stringify(parameters);
  
      const headers = this.client.toHeader(
        this.client.authorize(requestData, {
          key: options.key,
          secret: options.secret,
        })
      );
  
      const results = await Fetch(requestData.url, {
        method: 'POST',
        headers: Object.assign({}, baseHeaders, headers),
      })
        .then(res => res.text())
        .then(txt => querystring.parse(txt));
  
      return results;
    }
  
     // Tworzy zapytanie RESTowe do API
    makeRequest(method, resource, parameters) {
      const requestData = {
        url: `${this.url}/${resource}.json`,
        method,
      };
      if (parameters!==undefined)
        if (method === 'POST') requestData.data = parameters;
        else requestData.url += '?' + querystring.stringify(parameters);
  
      let headers = {};
          headers = this.client.toHeader(
          this.client.authorize(requestData, this.token)
        );
      return {
        requestData,
        headers,
      };
    }
  
    //
    get(resource, parameters) {
      const { requestData, headers } = this.makeRequest(
        'GET',
        resource,
        parameters
      );
  
      return Fetch(requestData.url, { headers })
        .then(Twitter._handleResponse)
        .then(results =>
          'errors' in results ? Promise.reject(results) : results
        );
    }
  
    //POST request, analogicznie do GET
    post(resource, body) {
      const { requestData, headers } = this.makeRequest(
        'POST',
        resource,
        body
      );
  
      const postHeaders = Object.assign({}, baseHeaders, headers);
      if (JSON_ENDPOINTS.includes(resource)) {
        body = JSON.stringify(body);
      } else {
        body = percentEncode(querystring.stringify(body));
        postHeaders['Content-Type'] = 'application/x-www-form-urlencoded';
      }
  
      return Fetch(requestData.url, {
        method: 'POST',
        headers: postHeaders,
        body,
      })
        .then(Twitter._handleResponse)
        .then(results =>
          'errors' in results ? Promise.reject(results) : results
        );
    }
}