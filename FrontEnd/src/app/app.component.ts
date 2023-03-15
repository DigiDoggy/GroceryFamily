import { Component, ElementRef, OnInit  } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Grocery } from './grocery';
import { GroceryService } from './grocery.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  // title(title: any) {
  //   throw new Error('Method not implemented.');
  // }

  public groceries: Grocery[] | undefined | null;
  public editGrocery:Grocery | undefined | null;
  public deleteGrocery:Grocery | undefined | null;
  

  constructor(private groceryService: GroceryService, private elRef: ElementRef){}

  ngOnInit(): void {
      this.getGroceries();
      this.addRow();
      this.checkBox();
      this.deleteRows();
  }





  public deleteRows(): void {
    const deleteButton = document.getElementById('deleteButton') as HTMLButtonElement;
  
    deleteButton.addEventListener('click', () => {
      const checkboxes = this.elRef.nativeElement.querySelectorAll('tbody input[type="checkbox"]:checked') as NodeListOf<HTMLInputElement>;
  
      checkboxes.forEach((checkbox) => {
        const row = checkbox.closest('tr');
        if (row) {
          const groceryCode = (row.querySelector('input[type="checkbox"]') as HTMLInputElement).id;
  
          this.groceryService.deleteGrocery(groceryCode).subscribe((response) => {
            console.log('Grocery with id', groceryCode, 'deleted:', response);
          });
  
          row.remove();
        }
      });
    });
  }

  public getGroceries():void{
    this.groceryService.getGroceries().subscribe(
      (Response: Grocery[])=>{
        this.groceries = Response;
        console.log(this.groceries);
      }
    )
  };

  public addRow(): void {
    const addButton = document.getElementById('addButton') as HTMLButtonElement;
    const tbody = document.querySelector('tbody') as HTMLTableSectionElement;
  
    addButton.addEventListener('click', () => {
      const row = tbody.insertRow();
      row.classList.add('td', 'table', 'td:last-child');
  
      const checkboxCell = row.insertCell();
      const checkbox = document.createElement('input');
      checkbox.type = 'checkbox';
      checkboxCell.appendChild(checkbox);
  
      const groceryNameCell = row.insertCell();
      const groceryName = document.createElement('td:last-child');
      groceryName.contentEditable = 'true';
      groceryNameCell.appendChild(groceryName);
  
      const quantityCell = row.insertCell();
      const quantity = document.createElement('td:last-child');
      quantity.contentEditable = 'true';
      quantityCell.appendChild(quantity);
  
      const rimiPriceCell = row.insertCell();
      const rimiPrice = document.createElement('td:last-child');
      rimiPriceCell.appendChild(rimiPrice);
  
      const prismaPriceCell = row.insertCell();
      const prismaPrice = document.createElement('td:last-child');
      prismaPriceCell.appendChild(prismaPrice);
  
      const barboraPriceCell = row.insertCell();
      const barboraPrice = document.createElement('td:last-child');
      barboraPriceCell.appendChild(barboraPrice);
    });
  }
  
  // window.addEventListener('load', () => {
  //   addRow();
  // });


  public checkBox(): void {
    const selectAllCheckbox = document.getElementById('selectAll') as HTMLInputElement;

    selectAllCheckbox.addEventListener('click', () => {
      const checkboxes = this.elRef.nativeElement.querySelectorAll('tbody input[type="checkbox"]') as NodeListOf<HTMLInputElement>;

      for (let i = 0; i < checkboxes.length; i++) {
        checkboxes[i].checked = selectAllCheckbox.checked;
      }
    });
  }
}

// import { Component, OnInit, AfterViewInit, Renderer2, ElementRef, ViewChild } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { Grocery } from './grocery';
// import { GroceryService } from './grocery.service';

// @Component({
//   selector: 'app-root',
//   templateUrl: './app.component.html',
//   styleUrls: ['./app.component.css']
// })
// export class AppComponent implements OnInit, AfterViewInit {
  
//   @ViewChild('checkboxes')
//   checkboxes!: ElementRef;

//   public groceries: Grocery[] | undefined | null;
//   public editGrocery: Grocery | undefined | null;
//   public deleteGrocery: Grocery | undefined | null;

//   constructor(private groceryService: GroceryService, private renderer: Renderer2) {}

//   ngOnInit(): void {
//       this.getGroceries();
//   }

//   public getGroceries(): void {
//     this.groceryService.getGroceries().subscribe((Response: Grocery[]) => {
//         this.groceries = Response;
//         console.log(this.groceries);
//     });
//   }

//   public ngAfterViewInit() {
//     const selectAllCheckbox = document.getElementById('selectAll') as HTMLInputElement;
//     const checkboxes = this.checkboxes.nativeElement.querySelectorAll('input[type="checkbox"]');

//     selectAllCheckbox.addEventListener('click', () => {
//       checkboxes.forEach((checkbox: any) => {
//         this.renderer.setProperty(checkbox, 'checked', selectAllCheckbox.checked);
//       });
//     });
//   }
// }
