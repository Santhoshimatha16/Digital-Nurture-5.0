import "./App.css";
import { useState } from "react";

import ThemeContext from "./ThemeContext";
import EmployeesList from "./EmployeesList";
import { EmployeesData } from "./Employee";

function App() {

    const [theme, setTheme] = useState("light");

    return (

        <ThemeContext.Provider value={theme}>

            <div>

                <label>Select a Theme : </label>

                <select
                    value={theme}
                    onChange={(e) => setTheme(e.target.value)}
                >
                    <option value="light">Light</option>

                    <option value="dark">Dark</option>

                </select>

                <hr />

                <EmployeesList employees={EmployeesData} />

            </div>

        </ThemeContext.Provider>

    );

}

export default App;