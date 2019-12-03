export class User {
  uzytkownik: string;
  nazwaUzytkownika: string;
  miejscowosc: string;
  obserwujacy: string;
  obserwowani: string;
  iloscPostow: string;

  constructor(uzytkownik: string, nazwaUzytkownika: string, miejscowosc: string, obserwujacy: string,
              obserwowani: string, iloscPostow: string) {
    this.uzytkownik = uzytkownik;
    this.nazwaUzytkownika = nazwaUzytkownika;
    this.miejscowosc = miejscowosc;
    this.obserwujacy = obserwujacy;
    this.obserwowani = obserwowani;
    this.iloscPostow = iloscPostow;
  }
}
