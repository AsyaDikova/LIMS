import React, { Component } from 'react';
import Input from '../common/Input';
import { register } from '../../api/remote';
import { withRouter } from 'react-router-dom';
import * as generator from "generate-password";


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
            repeatPassword: '',
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

        if(this.state.password !== this.state.repeatPassword){
            this.setState({
                error: "Password not equals!"
            });
            return;
        }

        const res = await register(this.state.email, this.state.password,
            this.state.firstName, this.state.lastName, this.state.phoneNumber, this.state.repeatPassword);

        if(!res.success){
            this.setState({error: res.message});
            return;
        }
        this.props.history.push('/');
    }

    render() {

        return (
            <div className="container">
                <h1>Register</h1>
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
                        label="First Name"
                    />
                    <Input
                        name="lastName"
                        value={this.state.lastName}
                        onChange={this.onChangeHandler}
                        label="Last Name"
                    />
                    <Input
                        name="phoneNumber"
                        value={this.state.phoneNumber}
                        onChange={this.onChangeHandler}
                        label="Telephone"
                    />
                    <Input
                        name="password"
                        type="text"
                        value={this.state.password}
                        onChange={this.onChangeHandler}
                        label="Password"
                    />
                    <Input
                        name="repeatPassword"
                        type="text"
                        value={this.state.repeatPassword}
                        onChange={this.onChangeHandler}
                        label="Repeat password"
                    />
                    <input type="submit" className="btn btn-primary" value="Register" />
                </form>
            </div>
        );
    }
}

export default withRouter(RegisterPage);