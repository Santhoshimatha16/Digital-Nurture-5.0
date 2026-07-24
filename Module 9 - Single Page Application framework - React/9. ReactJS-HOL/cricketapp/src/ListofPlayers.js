import React from "react";

function ListofPlayers() {

const players=[

{name:"Virat Kohli",score:98},
{name:"Rohit Sharma",score:88},
{name:"Shubman Gill",score:95},
{name:"KL Rahul",score:65},
{name:"Hardik Pandya",score:72},
{name:"Ravindra Jadeja",score:55},
{name:"MS Dhoni",score:89},
{name:"Rishabh Pant",score:69},
{name:"Yuvraj Singh",score:75},
{name:"Suresh Raina",score:60},
{name:"Sachin Tendulkar",score:100}

];

const below70=players.filter(player=>player.score<70);

return(

<div>

<h2>All Players</h2>

<table border="1">

<thead>

<tr>

<th>Name</th>

<th>Score</th>

</tr>

</thead>

<tbody>

{

players.map((player,index)=>(

<tr key={index}>

<td>{player.name}</td>

<td>{player.score}</td>

</tr>

))

}

</tbody>

</table>

<br/>

<h2>Players Scored Below 70</h2>

<ul>

{

below70.map((player,index)=>

<li key={index}>

{player.name} - {player.score}

</li>

)

}

</ul>

</div>

);

}

export default ListofPlayers;