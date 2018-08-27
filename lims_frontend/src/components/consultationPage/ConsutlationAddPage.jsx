import React, { Component } from 'react';
import Input from '../common/Input';
import { getAnalyzesName, createConsultation, getDaySchedulesHours } from '../../api/remote';
import { withRouter } from 'react-router-dom';


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
        this.onSelectDate = this.onSelectDate.bind(this);
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

        console.log(this.state);
    }

    onSelectDate(e) {
        this.setState({ [e.target.name]: e.target.value });

        console.log(this.state);

        // console.log(this.state);
        //
        // let currentDaySchedule;
        // for (let day of this.state.daySchedules) {
        //     if(day.currentDate = this.state.dateOfConsultation){
        //         currentDaySchedule = day.freeHours;
        //     }
        // }
        //
        // console.log(currentDaySchedule);
        //
        // this.setState({currentDaySchedule: currentDaySchedule});
    }

    onChangeHandler(e) {
        this.setState({ [e.target.name]: e.target.value });
        console.log(this.state);
    }

    async onSubmitHandler(e) {
        e.preventDefault();

        const res = await createConsultation(this.state.patientId, Number(this.state.analysisId), Number(this.state.hourOfConsultation), this.state.dateOfConsultation);

        if(!res.success){
            this.setState({error: res.message});
            return;
        }

        this.props.history.push('/');
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