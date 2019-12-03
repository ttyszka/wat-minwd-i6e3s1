const request = require('request-promise');
const $ = require('cheerio');
const sel = require('./selectors.json')
let params = [];
let fs = require('fs');
var writer = fs.createWriteStream('./'+process.argv[3]);
writer.write('{}');
let url = process.argv[2];
request(url)
    .then(function (html) {
        for (let index = 1; index <= $(sel.pages, html).last().text(); index++) {
            request(url+'?page='+index)
                .then(function (html) {
                    $(sel.product.entry, html).each(function (i, e) {
                        let entry = {
                            name: "",
                            parameters: [],
                            price: "",
                            img: ""
                        }
                        entry.name = ($(sel.product.name, this).text());
                        entry.price = ($(sel.product.price, this).text());
                        entry.img = ($(sel.product.img, this).attr('src'));
                        params = [];
                        $(sel.product.descEntry, this).each(function (j, e) {
                            params[j] = $(e).text();
                        })
                        entry.parameters = params;
                        request(entry.img)
                            .then(function (pic) {
                                entry.img = Buffer.from(pic, 'binary').toString('base64');
                                writer.write(','+JSON.stringify(entry))
                            }).catch(function (err) {})
                    });
                })
                .catch(function (err) {});
        }
    });