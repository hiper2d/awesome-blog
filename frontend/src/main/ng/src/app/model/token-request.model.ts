interface TokenRequestParam {
    username: string;
    password: string;
    scope: string;
    grant_type: string;
}

export class TokenRequest {
    private readonly username: string;
    private readonly password: string;
    private readonly scope: string;
    private readonly grant_type: string;

    constructor(param?: TokenRequestParam) {
        this.username = param ? param.username : 'anonymous';
        this.password = param ? param.password : '';
        this.scope = 'webclient';
        this.grant_type = 'password';
    }
}
