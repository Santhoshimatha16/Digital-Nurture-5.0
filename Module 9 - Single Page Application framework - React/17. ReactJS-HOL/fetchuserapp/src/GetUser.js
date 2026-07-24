import React, { Component } from 'react';

class GetUser extends Component {
  constructor(props) {
    super(props);
    this.state = {
      user: null
    };
  }

  componentDidMount() {
    fetch('https://api.randomuser.me/')
      .then(response => response.json())
      .then(data => {
        this.setState({
          user: data.results[0]
        });
      })
      .catch(error => console.log(error));
  }

  render() {
    const { user } = this.state;

    if (!user) {
      return <div>Loading...</div>;
    }

    return (
      <div>
        <h2>{user.name.title} {user.name.first}</h2>
        <img src={user.picture.large} alt="user" />
      </div>
    );
  }
}

export default GetUser;