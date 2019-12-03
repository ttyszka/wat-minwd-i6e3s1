import { TestBed } from '@angular/core/testing';

import { WordsMapService } from './words-map-service';

describe('WordsMapService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WordsMapService = TestBed.get(WordsMapService);
    expect(service).toBeTruthy();
  });
});
