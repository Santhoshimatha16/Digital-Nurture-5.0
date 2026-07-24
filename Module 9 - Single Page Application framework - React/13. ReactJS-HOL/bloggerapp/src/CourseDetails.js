import React from "react";

function CourseDetails(){

const courses=[

{
id:1,
name:"ReactJS",
duration:"30 Days"
},

{
id:2,
name:"Spring Boot",
duration:"45 Days"
},

{
id:3,
name:"Machine Learning",
duration:"60 Days"
}

];

return(

<div>

<h2>Course Details</h2>

<table border="1" cellPadding="10">

<thead>

<tr>

<th>ID</th>

<th>Course</th>

<th>Duration</th>

</tr>

</thead>

<tbody>

{

courses.map(course=>

<tr key={course.id}>

<td>{course.id}</td>

<td>{course.name}</td>

<td>{course.duration}</td>

</tr>

)

}

</tbody>

</table>

</div>

);

}

export default CourseDetails;