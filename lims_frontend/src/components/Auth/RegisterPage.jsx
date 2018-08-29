import React, { Component } from 'react';
import Input from '../common/Input';
import { register } from '../../api/remote';
import { withRouter } from 'react-router-dom';
import * as generator from "generate-password";
import { NotificationManager } from 'react-notifications';
import 'react-notifications/lib/notifications.css';


class RegisterPage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            email: '',
            password: generator.generate({
                length: 10,
                numbers: true
            }),
            firstName: '',
            lastName: '',
            phoneNumber: '',
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

        const res = await register(this.state.email, this.state.password,
            this.state.firstName, this.state.lastName, this.state.phoneNumber);

        if(!res.success){
            NotificationManager.error(res.message);
            return;
        } else {
            NotificationManager.success(res.message);
        }
        this.props.history.push('/');
    }

    render() {

        return (
            <div className="container">
                <h1>Register</h1>
                <form onSubmit={this.onSubmitHandler}>
                    <Input
                        required="required"
                        name="email"
                        value={this.state.email}
                        onChange={this.onChangeHandler}
                        label="E-mail"
                    />
                    <Input
                        required="required"
                        name="firstName"
                        value={this.state.firstName}
                        onChange={this.onChangeHandler}
                        label="First Name"
                    />
                    <Input
                        required="required"
                        name="lastName"
                        value={this.state.lastName}
                        onChange={this.onChangeHandler}
                        label="Last Name"
                    />
                    <Input
                        required="required"
                        name="phoneNumber"
                        value={this.state.phoneNumber}
                        onChange={this.onChangeHandler}
                        label="Telephone"
                    />
                    <Input
                        required="required"
                        name="password"
                        type="text"
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

export default withRouter(RegisterPage);