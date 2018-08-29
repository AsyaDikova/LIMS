import React, { Component } from 'react';
import Input from '../common/Input';
import { getAnalyzesName, createConsultation, getDaySchedulesHours } from '../../api/remote';
import { withRouter } from 'react-router-dom';
import { NotificationManager } from 'react-notifications';
import 'react-notifications/lib/notifications.css';


class ConsultationAddPage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            analysisId: '',
            hourOfConsultation: '',
            dateOfConsultation: '',
            analyzes: [],
            calendar: [],
            daySchedules: [],
            currentDaySchedule: [],
            patientId: Number(this.props.location.state.patientId),
            error: false
        };

        this.onChangeHandler = this.onChangeHandler.bind(this);
        this.onSubmitHandler = this.onSubmitHandler.bind(this);
    }

    componentDidMount() {
        this.getAnalysisNames();
    }

    async getAnalysisNames(){
        const data = await getAnalyzesName();
        this.setState({analyzes: data});
        this.setState({analysisId: data[0].id});

        const schedules = await getDaySchedulesHours(Number(this.state.analysisId));
        this.setState({daySchedules: schedules});
        this.setState({dateOfConsultation: schedules[0].currentDate });
        this.setState({hourOfConsultation: schedules[0].freeHours[0].hour});
        this.setState({currentDaySchedule: schedules[0].freeHours});

        console.log(this.state);
    }

    onChangeHandler(e) {
        this.setState({[e.target.name]: e.target.value});

        if(e.target.name === 'dateOfConsultation'){

            let currentDaySchedule = [];
            const stateDaySchedule = this.state.daySchedules;

            for (var d = 0; d < stateDaySchedule.length; d++) {
                console.log(stateDaySchedule[d]);
                console.log(this.state.daySchedules);
                if(stateDaySchedule[d].currentDate === e.target.value){
                    currentDaySchedule = stateDaySchedule[d].freeHours;
                }
            }

            this.setState({currentDaySchedule: currentDaySchedule});
        }
    }

    async onSubmitHandler(e) {
        e.preventDefault();

        const res = await createConsultation(this.state.patientId, Number(this.state.analysisId), Number(this.state.hourOfConsultation), this.state.dateOfConsultation);

        if(!res.success){
            NotificationManager.error(res.message);
            this.setState({error: res.message});
            return;
        } else {
            NotificationManager.info('consultation: ' + res.consultation.id);
        }

        this.props.history.push({pathname: '/'});
    }

    render() {

        return (
            <div className="container">
                <h1>Create Consultation</h1>
                <div>{this.state.error}</div>
                <form onSubmit={this.onSubmitHandler}>
                    <label> Analyses </label>
                    <select name="analysisId" onChange={this.onChangeHandler}>
                        {this.state.analyzes.map(a => <option value={a.id} key={a.id}>{a.name}</option>)}
                    </select>

                    <Input
                        name="dateOfConsultation"
                        type="date"
                        value={this.state.dateOfConsultation}
                        onChange={this.onChangeHandler}
                        label="Consultation Date"
                    />


                    <label> Free Hours for Consultation  </label>
                    <select name="hourOfConsultation" onChange={this.onChangeHandler}>
                        {this.state.currentDaySchedule.map(a => <option value={a.hour} key={a.hour}>{a.hour}</option>)}
                    </select>
                    <input type="submit" className="btn btn-primary" value="Create Consultation" />
                </form>
            </div>
        );
    }
}

export default withRouter(ConsultationAddPage)