import React, { Component } from 'react';
import { getEmployeeDetails } from '../../api/remote';

export default class EmployeeDetailsPage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            employee: '',
            error: false
        };
    }

    componentDidMount() {
        this.getData();
    }

    async getData() {
        const res = await getEmployeeDetails();
        this.setState({ employee: res });
    }


    render() {
        let main = <p>Loading &hellip;</p>;
        if (this.state.employee) {
            const employee = this.state.employee;
            main = (
                <div className="employeeDetails">
                    <h2>Username: {employee.username}</h2>
                    <h3>Email: {employee.email}</h3>
                    <h3>First name: {employee.firstName}</h3>
                    <h3>Last name: {employee.lastName}</h3>
                    <h3>Telephone {employee.telephone}</h3>
                </div>
            );
        }

        return (
            <div className="container">
                <h1>Details Page</h1>
                {main}
            </div>
        );
    }
}