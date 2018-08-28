import React, { Component } from 'react';
import Input from '../common/Input';
import { registerPatient } from '../../api/remote';
import { withRouter } from 'react-router-dom';
import * as generator from "generate-password";


class PatientRegisterPage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            email: '',
            firstName: '',
            lastName: '',
            phoneNumber: '',
            password:  generator.generate({
                length: 10,
                numbers: true
            }),
            error: false
        };

        this.onChangeHandler = this.onChangeHandler.bind(this);
        this.onSubmitHandler = this.onSubmitHandler.bind(this);
    }

    onChangeHandler(e) {
        this.setState({ [e.target.name]: e.target.value });
    }

    async onSubmitHandler(e) {
        e.preventDefault();

        const res = await registerPatient(this.state.email, this.state.firstName, this.state.lastName, this.state.phoneNumber, this.state.password);

        if(!res.success){
            this.setState({error: res.message});
            return;
        }

        this.state.emailPatient = res.object.email;

        this.props.history.push('/occurrence/create');
    }

    render() {

        return (
            <div className="container">
                <h1>Register Patient</h1>
                <div>{this.state.error}</div>
                <form onSubmit={this.onSubmitHandler}>
                    <Input
                        name="email"
                        value={this.state.email}
                        onChange={this.onChangeHandler}
                        label="E-mail"
                    />
                    <Input
                        name="firstName"
                        value={this.state.firstName}
                        onChange={this.onChangeHandler}
                        label="FirstName"
                    />
                    <Input
                        name="lastName"
                        value={this.state.lastName}
                        onChange={this.onChangeHandler}
                        label="LastName"
                    />
                    <Input
                        name="phoneNumber"
                        value={this.state.phoneNumber}
                        onChange={this.onChangeHandler}
                        label="Phone Number"
                    />
                    <Input
                        name="password"
                        value={this.state.password}
                        onChange={this.onChangeHandler}
                        label="Password"
                    />
                    <input type="submit" className="btn btn-primary" value="Register" />
                </form>
            </div>
        );
    }
}

export default withRouter(PatientRegisterPage);