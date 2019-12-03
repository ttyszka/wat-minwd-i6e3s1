export class Tags {
  tag: string;
  occurrences: number;

  constructor(tag: string, occurrences: number) {
    this.tag = tag;
    this.occurrences = occurrences;
  }
}
