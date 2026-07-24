import React from "react";

function BookDetails() {

const books=[

{
id:1,
name:"React Basics",
author:"Jordan Walke",
price:450
},

{
id:2,
name:"Java Programming",
author:"James Gosling",
price:650
},

{
id:3,
name:"Python Guide",
author:"Guido Van Rossum",
price:550
}

];

return(

<div>

<h2>Book Details</h2>

<table border="1" cellPadding="10">

<thead>

<tr>

<th>ID</th>

<th>Book</th>

<th>Author</th>

<th>Price</th>

</tr>

</thead>

<tbody>

{

books.map(book=>

<tr key={book.id}>

<td>{book.id}</td>

<td>{book.name}</td>

<td>{book.author}</td>

<td>₹{book.price}</td>

</tr>

)

}

</tbody>

</table>

</div>

);

}

export default BookDetails;