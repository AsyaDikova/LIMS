import React, { Component } from 'react';
import { getPatientConsultations } from '../../api/remote';
import {withRouter} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';

class PatientConsultationsPage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            consultations: []
        };
    }

    componentDidMount() {
        this.getData();
    }


    async getData() {
        const data = await getPatientConsultations();
        this.setState({ consultations: data.consultations });
        console.log(this.state.consultations);
    }


    render() {

        return (
            <div className="container">
                <h2>My Consultations</h2>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">By Analysis</th>
                        <th scope="col">Date</th>
                        <th scope="col">Hour</th>
                    </tr>
                    </thead>
                    <tbody>


                {this.state.consultations.map(t => (
                    <tr>
                        <td>{t.analysisName}</td>
                        <td>{t.dateOfConsultation}</td>
                        <td>{t.hour}</td>
                    </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        );
    }
}

export default withRouter(PatientConsultationsPage)