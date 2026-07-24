import React from "react";

function BlogDetails(){

const blogs=[

{
id:1,
title:"Introduction to React",
author:"Santhoshi"
},

{
id:2,
title:"Learning JavaScript",
author:"John"
},

{
id:3,
title:"React Router Guide",
author:"David"
}

];

return(

<div>

<h2>Blog Details</h2>

<ul>

{

blogs.map(blog=>

<li key={blog.id}>

{blog.title} - {blog.author}

</li>

)

}

</ul>

</div>

);

}

export default BlogDetails;