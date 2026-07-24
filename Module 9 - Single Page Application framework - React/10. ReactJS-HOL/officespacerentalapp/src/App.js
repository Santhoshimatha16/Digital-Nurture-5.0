import officeImage from "./office.jpeg";

function App() {

  const offices = [

    {
      name: "Tech Park",
      rent: 45000,
      address: "Chennai"
    },

    {
      name: "Business Hub",
      rent: 75000,
      address: "Bangalore"
    },

    {
      name: "Startup Space",
      rent: 58000,
      address: "Hyderabad"
    },

    {
      name: "Corporate Tower",
      rent: 90000,
      address: "Mumbai"
    }

  ];

  return (

    <div style={{padding:"20px"}}>

      <h1>Office Space Rental Application</h1>

      <img
        src={officeImage}
        alt="Office"
        width="500"
      />

      <hr/>

      <h2>Available Office Spaces</h2>

      {

        offices.map((office,index)=>(

          <div
            key={index}
            style={{
              border:"1px solid black",
              padding:"15px",
              marginBottom:"15px"
            }}
          >

            <h3>{office.name}</h3>

            <p>

              <strong>Address :</strong>

              {office.address}

            </p>

            <p>

              <strong>Rent :</strong>

              <span

                style={{

                  color: office.rent < 60000 ? "red" : "green",

                  fontWeight:"bold"

                }}

              >

                ₹{office.rent}

              </span>

            </p>

          </div>

        ))

      }

    </div>

  );

}

export default App;