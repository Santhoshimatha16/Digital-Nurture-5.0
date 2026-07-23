import CohortDetails from "./Components/CohortDetails";

function App() {

    return (

        <div>

            <h1>Cognizant Academy Dashboard</h1>

            <CohortDetails
                name="Java Full Stack"
                trainer="Rajesh"
                status="Ongoing"
                strength={35}
                startDate="20-07-2026"
            />

            <CohortDetails
                name="React Training"
                trainer="Priya"
                status="Completed"
                strength={40}
                startDate="01-06-2026"
            />

            <CohortDetails
                name="Spring Boot"
                trainer="Arun"
                status="Ongoing"
                strength={28}
                startDate="10-07-2026"
            />

        </div>

    );

}

export default App;