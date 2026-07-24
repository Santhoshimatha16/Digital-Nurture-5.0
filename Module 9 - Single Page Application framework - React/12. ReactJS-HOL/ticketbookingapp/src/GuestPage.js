import React from "react";
import FlightDetails from "./FlightDetails";

function GuestPage() {

    return (

        <div>

            <h2>Welcome Guest</h2>

            <p>You can view available flights.</p>

            <FlightDetails/>

            <h3>Please Login to Book Tickets.</h3>

        </div>

    );

}

export default GuestPage;