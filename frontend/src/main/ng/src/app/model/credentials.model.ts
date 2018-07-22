interface CredentialsParam {
  username: string;
  password: string;
}

export class Credentials {
  private readonly _username: string;
  private readonly _password: string;

  constructor(param?: CredentialsParam) {
    this._username = param ? param.username : '';
    this._password = param ? param.password : '';
  }

  get username() {
    return this._username;
  }

  get password() {
    return this._password;
  }
}
