import { Temperature } from 'src/api/models';

export class CreateTemperatureAction {
  static readonly type = '[Temperature] CreateTemperatureAction';
  constructor(public temperature: Temperature) { }
}
export class ListTemperatureAction{
  static readonly type = '[Temperature] ListTemperatureAction';
  constructor() {}
}
