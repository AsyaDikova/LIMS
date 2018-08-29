import React, { Component } from 'react';
import Input from '../common/Input';
import { registerPatient, getPatientIsExist } from '../../api/remote';
import { withRouter } from 'react-router-dom';
import * as generator from "generate-password";
import { NotificationManager } from 'react-notifications';
import 'react-notifications/lib/notifications.css';


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
            isExist: false,
            patientExistFlag: false,
            patientId: '',
            isConsultation: false,
            error: false
        };

        this.onChangeHandler = this.onChangeHandler.bind(this);
        this.onSubmitHandler = this.onSubmitHandler.bind(this);
    }

    componentDidMount() {
        this.setState({isExist: false,
            patientExistFlag: false,
            patientId: '',
            isConsultation: false  });
    }


    onChangeHandler(e) {
        this.setState({ [e.target.name]: e.target.value });
    }



    async onSubmitHandler(e) {
        e.preventDefault();

        if(!this.state.isExist && !this.state.patientExistFlag){
            const resData = await getPatientIsExist(this.state.email);

            this.setState({isExist : resData.isExist});
            this.setState({patientExistFlag : true});

            if(resData.isExist){
                this.setState({patientId : resData.patientId});
            }
        } else if(!this.state.isExist && this.state.patientExistFlag) {

            const res = await registerPatient(this.state.email, this.state.firstName, this.state.lastName, this.state.phoneNumber, this.state.password);

            if (!res.success) {
                NotificationManager.error(res.message);
                return;
            } else {
                NotificationManager.success(res.message);
            }

            let pathRedirect = this.state.isConsultation ? '/consultation/create' : '/analysisResult/create';

            this.setState({patientId: res.patientId});
            this.props.history.push({
                pathname: pathRedirect,
                state: {
                    patientId: this.state.patientId
                }
            })
        } else {
            let pathRedirect = this.state.isConsultation ? '/consultation/create' : '/analysisResult/create';

            this.props.history.push({
                pathname: pathRedirect,
                state: {
                    patientId: this.state.patientId
                }
            })
        }
    }

    render() {

        return (
            <div className="container">
                <h1>Register Patient</h1>
                <div>{this.state.error}</div>
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
                        label="FirstName"
                    />
                    <Input
                        required="required"
                        name="lastName"
                        value={this.state.lastName}
                        onChange={this.onChangeHandler}
                        label="LastName"
                    />
                    <Input
                        required="required"
                        name="phoneNumber"
                        value={this.state.phoneNumber}
                        onChange={this.onChangeHandler}
                        label="Phone Number"
                    />
                    <Input
                        required="required"
                        name="password"
                        value={this.state.password}
                        onChange={this.onChangeHandler}
                        label="Password"
                    />
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="isConsultation"
                               value={this.state.isConsultation} onChange={() => {this.state.isConsultation = !this.state.isConsultation }} />
                            <label class="form-check-label" for="isConsultation">Consultation</label>
                    </div>
                    <input type="submit" className="btn btn-primary" value={!this.state.isExist ? "Register Patient" : "Continue"} />
                </form>
            </div>
        );
    }
}

export default withRouter(PatientRegisterPage);