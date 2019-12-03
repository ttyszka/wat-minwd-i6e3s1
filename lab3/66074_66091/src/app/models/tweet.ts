export class Tweet {
  createTime: string;
  message: string;
  lang: string;
  retweeted: boolean;

  constructor(createTime: string, message: string, lang: string, retweeted: boolean) {
    this.createTime = createTime;
    this.message = message;
    this.lang = lang;
    this.retweeted = retweeted;
  }
}
