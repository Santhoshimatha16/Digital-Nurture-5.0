import styles from "./CohortDetails.module.css";

function CohortDetails(props) {

    return (

        <div className={styles.box}>

            <h2
                style={{
                    color:
                        props.status === "Ongoing"
                            ? "green"
                            : "blue"
                }}
            >
                {props.name}
            </h2>

            <dl>

                <dt>Trainer</dt>
                <dd>{props.trainer}</dd>

                <dt>Status</dt>
                <dd>{props.status}</dd>

                <dt>Strength</dt>
                <dd>{props.strength}</dd>

                <dt>Start Date</dt>
                <dd>{props.startDate}</dd>

            </dl>

        </div>

    );

}

export default CohortDetails;