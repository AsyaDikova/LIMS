import React, { Component } from 'react';
import {withRouter} from "react-router-dom";
import {createAnalysisResult, getAnalyzes} from "../../api/remote";
import { NotificationManager } from 'react-notifications';
import 'react-notifications/lib/notifications.css';

class AnalysisResultAddPage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            analysisId: '',
            analyzes: [],
            periodOfProduct: '',
            patientId: Number(this.props.location.state.patientId),
            error: false
        };

        this.onChangeHandler = this.onChangeHandler.bind(this);
        this.onSubmitHandler = this.onSubmitHandler.bind(this);
        this.onCheckAnalysis = this.onCheckAnalysis.bind(this);
    }

    componentDidMount() {
        this.getAnalyzes();
    }

    async getAnalyzes(){
        const data = await getAnalyzes();
        this.setState({analyzes: data.analyzes});


        this.setState({analysisId: data.analyzes[0].id, periodOfProduct: data.analyzes[0].periodOfProduct});
    }


    onChangeHandler(e) {
        this.setState({ [e.target.name]: e.target.value });
    }

    onCheckAnalysis(e) {
        this.setState({ [e.target.name]: e.target.value });
        this.setState ({periodOfProduct : this.state.analyzes[e.target.value-1].periodOfProduct})
    }

    async onSubmitHandler(e) {
        e.preventDefault();

        const res = await createAnalysisResult(this.state.patientId, Number(this.state.analysisId));

        if(!res.success){
            NotificationManager.error(res.message);
            this.setState({error: res.message});
            return;
        } else {
            NotificationManager.info('Correct register analysis result');
        }

        this.props.history.push({pathname: '/'});
    }

    render() {

        return (
            <div className="container">
                <h1>Create Analysis Result</h1>
                <div>{this.state.error}</div>
                <form onSubmit={this.onSubmitHandler}>
                    <div class="form-group">
                        <label for="analysis">Analysis</label>
                        <select class="form-control" name="analysisId" onChange={this.onCheckAnalysis}>
                            {this.state.analyzes.map(a => <option value={a.id} key={a.id}>{a.name}</option>)}
                        </select>
                    </div>
                    <div>This analysis is ready for <span> {this.state.periodOfProduct} </span>days.</div>
                    <input type="submit" className="btn btn-primary" value="Create Analysis Result" />
                </form>
            </div>
        );
    }
}

export default withRouter(AnalysisResultAddPage)