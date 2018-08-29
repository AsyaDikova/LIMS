import React, { Component } from 'react';
import { getEmployeeDaySchedulesHours } from '../../api/remote';
import { withRouter } from 'react-router-dom';
import DayScheduleList from "./DayScheduleList";


class EmployeeSchedule extends Component {
    constructor(props) {
        super(props);

        this.state = {
            currentDate: '',
            allDates: [],
            calendar: [],
            currentDaySchedule: [],
            error: false
        };

        this.onChangeHandler = this.onChangeHandler.bind(this);
    }

    componentDidMount() {
        this.getEmployeeSchedule();
    }

    async getEmployeeSchedule(){
        const data = await getEmployeeDaySchedulesHours();

        this.setState({calendar: data});
        this.setState({currentDate: data[0].currentDate });
        this.setState({currentDaySchedule: data[0].hours});

        let allDate = [];
        console.log(data);

        for (var i = 0; i < data.length; i++) {
            allDate.push({date: data[i].currentDate});
        }

        console.log(allDate);

        this.setState({allDates: allDate});
    }

    onChangeHandler(e) {
        this.setState({[e.target.name]: e.target.value});

        if(e.target.name === 'currentDate'){

            let currentDaySchedule = [];
            const calendar = this.state.calendar;

            for (var d = 0; d < calendar.length; d++) {
                if(calendar[d].currentDate === e.target.value){
                    currentDaySchedule = calendar[d].hours;
                }
            }

            this.setState({currentDaySchedule: currentDaySchedule});
        }
    }

    render() {

        return (
            <div className="container">
                <h1>Day Schedule Calendar</h1>
                <div>{this.state.error}</div>

                <div class="form-group">
                    <label for="exampleSelect1">Select Day</label>
                    <select name="currentDate" onChange={this.onChangeHandler} class="form-control mr-sm-2" >
                        {this.state.allDates.map(a => <option value={a.date} key={a.date}>{a.date}</option>)}
                    </select>
                </div>

                <table class="table">
                    <caption>Day Schedule for {this.state.currentDate}</caption>
                    <thead>
                    <tr>
                        <th scope="col">hour</th>
                        <th scope="col">Consultation for Analysis</th>
                        <th scope="col">Patient Name</th>
                    </tr>
                    </thead>
                    <tbody>
                        {this.state.currentDaySchedule.map(a => <DayScheduleList hour={a.hour} patientName={a.patientName} analysisName={a.analysisName}/>)}
                    </tbody>
                </table>
            </div>
        );
    }
}

export default withRouter(EmployeeSchedule)