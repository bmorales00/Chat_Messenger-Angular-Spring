import { SafeUrl, DomSanitizer } from '@angular/platform-browser';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  img!:SafeUrl;

  constructor(private http:HttpClient, private sanitizer:DomSanitizer) {}


  fetchImg(url:string): Observable<SafeUrl>{
    const apiUrl = `http://localhost:8080/api/${url}`;

    return this.http.get(apiUrl, {responseType:'blob'}).pipe(
      map((response) => {
        const objUrl:string = URL.createObjectURL(response);
        return this.sanitizer.bypassSecurityTrustResourceUrl(objUrl);
      })
    )
  }

}
