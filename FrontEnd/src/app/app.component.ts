import { Component, OnInit, AfterViewInit  } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Grocery } from './grocery';
import { GroceryService } from './grocery.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit,AfterViewInit {
  title(title: any) {
    throw new Error('Method not implemented.');
  }

  public groceries: Grocery[] | undefined | null;
  public editGrocery:Grocery | undefined | null;
  public deleteGrocery:Grocery | undefined | null;
  

  constructor(private groceryService: GroceryService){}

  ngOnInit(): void {
      this.getGroceries();
  }

  public getGroceries():void{
    this.groceryService.getGroceries().subscribe(
      (Response: Grocery[])=>{
        this.groceries = Response;
        console.log(this.groceries);
      }
    )
  };

  public ngAfterViewInit(){
    const selectAllCheckbox = document.getElementById('selectAll') as HTMLInputElement;
const checkboxes = document.querySelectorAll('tbody input[type="checkbox"]' ) as NodeListOf<HTMLInputElement>;

selectAllCheckbox.addEventListener('click', () => {
  console.log(checkboxes.length);
  
  for (let i = 0; i < checkboxes.length; i++) {
    checkboxes[i].checked = selectAllCheckbox.checked;
  }
});
  }
}
