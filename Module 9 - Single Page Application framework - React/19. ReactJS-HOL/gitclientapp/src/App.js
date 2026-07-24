import React, { Component } from 'react';
import GitClient from './GitClient';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      repositories: []
    };
    this.gitClient = new GitClient();
  }

  componentDidMount() {
    this.gitClient.getRepositories('techiesyed').then(repos => {
      this.setState({ repositories: repos });
    });
  }

  render() {
    return (
      <div className="App">
        <h2>Repositories</h2>
        <ul>
          {this.state.repositories.map((repo, index) => (
            <li key={index}>{repo}</li>
          ))}
        </ul>
      </div>
    );
  }
}

export default App;