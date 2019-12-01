import { TestBed } from '@angular/core/testing';

import { HttpClientUtilsService } from './http-client-utils.service';

describe('HttpUtilsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HttpClientUtilsService = TestBed.get(HttpClientUtilsService);
    expect(service).toBeTruthy();
  });
});
