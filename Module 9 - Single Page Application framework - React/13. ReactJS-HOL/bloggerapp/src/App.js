import React from "react";

import BookDetails from "./BookDetails";
import BlogDetails from "./BlogDetails";
import CourseDetails from "./CourseDetails";

function App(){

const choice="blog";

let component;

if(choice==="book")

component=<BookDetails/>;

else if(choice==="blog")

component=<BlogDetails/>;

else

component=<CourseDetails/>;

return(

<div>

<h1>Blogger Application</h1>

{component}

</div>


);

}

export default App;