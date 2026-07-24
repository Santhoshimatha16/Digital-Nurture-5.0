import React, { Component } from "react";

class Register extends Component {

  constructor() {
    super();

    this.state = {
      name: "",
      email: "",
      password: "",
      errors: {}
    };
  }

  handleChange = (event) => {

    const { name, value } = event.target;

    this.setState(
      {
        [name]: value
      },
      this.validateField
    );
  };

  validateField = () => {

    const errors = {};

    if (this.state.name.length < 5) {
      errors.name = "Name must contain at least 5 characters";
    }

    if (
      !this.state.email.includes("@") ||
      !this.state.email.includes(".")
    ) {
      errors.email = "Enter a valid Email";
    }

    if (this.state.password.length < 8) {
      errors.password = "Password must contain at least 8 characters";
    }

    this.setState({ errors });
  };

  handleSubmit = (event) => {

    event.preventDefault();

    this.validateField();

    if (Object.keys(this.state.errors).length === 0) {

      alert("Registration Successful!");

      this.setState({
        name: "",
        email: "",
        password: "",
        errors: {}
      });

    }
    else {

      alert("Please correct the errors.");

    }
  };

  render() {

    return (

      <div style={{ width: "400px", margin: "30px auto" }}>

        <h2>Mail Registration</h2>

        <form onSubmit={this.handleSubmit}>

          <label>Name</label>

          <br />

          <input
            type="text"
            name="name"
            value={this.state.name}
            onChange={this.handleChange}
          />

          <br />

          <span style={{ color: "red" }}>
            {this.state.errors.name}
          </span>

          <br /><br />

          <label>Email</label>

          <br />

          <input
            type="email"
            name="email"
            value={this.state.email}
            onChange={this.handleChange}
          />

          <br />

          <span style={{ color: "red" }}>
            {this.state.errors.email}
          </span>

          <br /><br />

          <label>Password</label>

          <br />

          <input
            type="password"
            name="password"
            value={this.state.password}
            onChange={this.handleChange}
          />

          <br />

          <span style={{ color: "red" }}>
            {this.state.errors.password}
          </span>

          <br /><br />

          <button type="submit">

            Register

          </button>

        </form>

      </div>

    );
  }
}

export default Register;