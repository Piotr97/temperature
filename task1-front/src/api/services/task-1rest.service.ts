/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { Temperature } from '../models/temperature';

/**
 * Task 1 Rest
 */
@Injectable({
  providedIn: 'root',
})
class Task1RestService extends __BaseService {
  static readonly postValueUsingPOSTPath = '/temperature/value';
  static readonly getValuesUsingGETPath = '/temperature/values';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * postValue
   * @param temperature temperature
   * @return OK
   */
  postValueUsingPOSTResponse(temperature: Temperature): __Observable<__StrictHttpResponse<Temperature>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = temperature;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/temperature/value`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Temperature>;
      })
    );
  }
  /**
   * postValue
   * @param temperature temperature
   * @return OK
   */
  postValueUsingPOST(temperature: Temperature): __Observable<Temperature> {
    return this.postValueUsingPOSTResponse(temperature).pipe(
      __map(_r => _r.body as Temperature)
    );
  }

  /**
   * getValues
   * @return OK
   */
  getValuesUsingGETResponse(): __Observable<__StrictHttpResponse<Array<Temperature>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/temperature/values`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<Temperature>>;
      })
    );
  }
  /**
   * getValues
   * @return OK
   */
  getValuesUsingGET(): __Observable<Array<Temperature>> {
    return this.getValuesUsingGETResponse().pipe(
      __map(_r => _r.body as Array<Temperature>)
    );
  }
}

module Task1RestService {
}

export { Task1RestService }
