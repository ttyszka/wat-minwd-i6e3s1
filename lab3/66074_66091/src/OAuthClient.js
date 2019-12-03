const crypto = require('crypto');
const OAuth = require('oauth-1.0a');
module.exports = function ({ key, secret }) {
    const client = OAuth({
      consumer: { key, secret },
      signature_method: 'HMAC-SHA1',
      hash_function(baseString, key) {
        return crypto
          .createHmac('sha1', key)
          .update(baseString)
          .digest('base64');
      },
    });
  
    return client;
  };