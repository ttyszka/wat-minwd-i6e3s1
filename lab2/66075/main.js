#!/usr/bin/env node
let Scraper = require('simple-twitter-scraper').scraper,
	moment = require('moment'),
	fs = require('fs'),
	path = require('path'),
	_ = require('lodash');
let keyword =process.argv[2],
	start = process.argv[3],
	end = process.argv[4],
	basedir = process.argv[5];
let timeEnd = moment(new Date()),
	timeStart = moment(timeEnd).add(-30, 'days');
let helpText = `=============================================================
(How to use)
node index.js <keyword(keyword)> <First date(first date)> <Last date (last date)> <Directory(output directory)>
-Keywords: Twitter keywords to import 
-First date (YYYY-MM-DD): First date to import keywords 
-Last date (YYYY-MM-DD): Last date to import keywords Directory: Directory location to save

사용 예(Example)
> node index.js Twitter ${timeStart.format('YYYY-MM-DD')} ${timeEnd.format('YYYY-MM-DD')} ./
=============================================================
`;
let argNames = ['keyword (keyword)', 'First date(first date)', 'Last Dat(last date)', 'Directory(output directory)'],
	argChecker = [arg => {try {if (arg.length <1) {throw new Error (); }} catch (e) {throw new Error ("Length should be more than 1.)"); }}, arg => {try {moment (arg); } 
catch (e) {throw new Error ("Invalid date format. (YYYY-MM-DD))"); }}, arg => {try {moment (arg); } catch (e) {throw new Error ("Invalid date format. (YYYY-MM-DD))"); }}, 
arg => {try {if (! fs.lstatSync (arg) .isDirectory ()) {throw new Error (); }} catch (e) {throw new Error ("Directory is not exist.)"); }}];
[keyword, start, end, basedir] .forEach ((arg, i) => { if (typeof (arg)! = 'string') { console.log (`Error: The $ {i + 1} th argument <$ {argNames [i]}> is not specified. (Not specified argument.)`); 
console.log (helpText); process.exit (1);
	}
	try {
		argChecker[i](arg);
	} catch (e) {
		console.log(`Error: ${i+1} The first argument
 <${argNames[i]}>Is not specified.(Invalid argument.)`);
		console.log(`Error: ${e.message}`);
		console.log(helpText);
		process.exit(1);
	}
});
let	dummyStart = moment(start).add(-1, 'days').format('YYYY-MM-DD'),
	dummyEnd = moment(end).add(1, 'days').format('YYYY-MM-DD');
let query = `${keyword} since:${dummyStart} until:${dummyEnd}`;
let scraper = new Scraper(query);

const EXT = '.json';
function getCurrentTime() {
	return moment(new Date()).format('MM-DD HH:mm:ss');
}

scraper.interceptor = function (tweets) {
	let twData = {};
	let currentTime = getCurrentTime();
	for (var tweet of tweets) {
		let name = tweet.date.split(' ')[0],
			filePath = path.resolve(basedir, name.concat(EXT));
		if ((name == dummyStart) ||
			(name == dummyEnd)) {
			console.log(`[${currentTime}]: Ignored ${name} (dummyStart: ${dummyStart}, dummyEnd: ${dummyEnd})`);
			return;
		}
		if (twData[filePath] == null) {
			twData[filePath] = [];
		}
		twData[filePath].push(tweet);
	}
	_.forEach(twData, (dataArray, filePath) => {
		let data = dataArray;
		if (fs.existsSync(filePath)) {
			let beforeData = fs.readFileSync(filePath);
			data = JSON.parse(beforeData).concat(data);
		}
		fs.writeFileSync(filePath, JSON.stringify(data));
		console.log(`[${currentTime}]: Saved ${dataArray.length} tweets in ${filePath}`);
	});
};
scraper.start();
//start