import { CreateTemperatureAction, ListTemperatureAction } from './temperature.actions';
import { Injectable } from '@angular/core';
import { State, Action, StateContext } from '@ngxs/store';
import { Task1RestService } from 'src/api/services';
import { tap } from 'rxjs/operators';
import { Temperature } from 'src/api/models';
import { Navigate } from '@ngxs/router-plugin';


export class TemperatureStateModel {
  public temperatureList : Temperature[];
}

const defaults = {
  temperatureList: []
};

@State<TemperatureStateModel>({
  name: 'temperature',
  defaults
})
@Injectable()
export class TemperatureState {
  constructor(public task1RestService: Task1RestService ){}
  @Action(CreateTemperatureAction)
  create( ctx: StateContext<TemperatureStateModel>, { temperature }: CreateTemperatureAction) {
    return this.task1RestService.postValueUsingPOST(temperature).pipe(
      tap(response => ctx.dispatch(new Navigate(['/list'])))
    );
  }

  @Action(ListTemperatureAction)
  listTemperature({ getState, setState }: StateContext<TemperatureStateModel>, { }: ListTemperatureAction) {
    return this.task1RestService.getValuesUsingGET().pipe(
      tap(response => setState({temperatureList:response}))
    );
  }
}
