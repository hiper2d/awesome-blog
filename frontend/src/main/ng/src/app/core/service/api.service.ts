import {AbstractService} from "./abstract-service";
import {Observable} from "rxjs/internal/Observable";
import {ApiConst} from "../../util/api.const";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class ApiService extends AbstractService {

    constructor(http: HttpClient) {
        super(http);
    }

    echo(): Observable<string> {
        return this.text(ApiConst.ECHO)
    }
}