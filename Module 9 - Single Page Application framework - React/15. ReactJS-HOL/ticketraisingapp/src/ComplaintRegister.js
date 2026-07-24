import React, { Component } from "react";

class ComplaintRegister extends Component {

    constructor() {

        super();

        this.state = {

            employeeName: "",

            complaint: ""

        };

    }

    handleNameChange = (event) => {

        this.setState({

            employeeName: event.target.value

        });

    }

    handleComplaintChange = (event) => {

        this.setState({

            complaint: event.target.value

        });

    }

    handleSubmit = (event) => {

        event.preventDefault();

        const refNo = Math.floor(Math.random() * 100000);

        alert(

            "Complaint Submitted Successfully!\n\n" +

            "Employee : " + this.state.employeeName +

            "\nReference Number : " + refNo

        );

        this.setState({

            employeeName: "",

            complaint: ""

        });

    }

    render() {

        return (

            <div style={{ width: "400px", margin: "30px auto" }}>

                <h2>Ticket Raising Application</h2>

                <form onSubmit={this.handleSubmit}>

                    <label>Employee Name</label>

                    <br />

                    <input

                        type="text"

                        value={this.state.employeeName}

                        onChange={this.handleNameChange}

                        required

                    />

                    <br /><br />

                    <label>Complaint</label>

                    <br />

                    <textarea

                        rows="5"

                        cols="40"

                        value={this.state.complaint}

                        onChange={this.handleComplaintChange}

                        required

                    />

                    <br /><br />

                    <button type="submit">

                        Submit Complaint

                    </button>

                </form>

            </div>

        );

    }

}

export default ComplaintRegister;