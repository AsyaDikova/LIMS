import React, { Component } from 'react';
import Input from '../common/Input';
import { createAnalyses } from '../../api/remote';
import { withRouter } from 'react-router-dom';
import { NotificationManager } from 'react-notifications';
import 'react-notifications/lib/notifications.css';


class AnalysesAddPage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            name: '',
            price: '',
            description: '',
            type:'GENETIC_PREDISPOSITION',
            periodOfProduct: '',
            employees: [],
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

        const res = await createAnalyses(this.state.name, Number(this.state.price), this.state.description,
            Number(this.state.periodOfProduct), this.state.type);

        if(!res.success){
            NotificationManager.error(res.message);
            this.setState({error: res.message});
            return;
        } else {
            NotificationManager.info('Correct make analysis.');
        }
        this.props.history.push('/');
    }

    render() {

        return (
            <div className="container">
                <h1>Create Analyses</h1>
                <div>{this.state.error}</div>
                <form onSubmit={this.onSubmitHandler}>
                    <Input
                        name="name"
                        value={this.state.name}
                        type="text"
                        onChange={this.onChangeHandler}
                        label="Name"
                    />
                    <div>
                        <label htmlFor="price">Price</label>
                        <input
                            name="price"
                            value={this.state.price}
                            type="number"
                            step="0.01"
                            onChange={this.onChangeHandler}
                        />
                    </div>
                    <Input
                        name="description"
                        type="text"
                        value={this.state.description}
                        onChange={this.onChangeHandler}
                        label="Description"
                    />
                    <Input
                        name="periodOfProduct"
                        type="number"
                        value={this.state.periodOfProduct}
                        onChange={this.onChangeHandler}
                        label="periodOfProduct"
                    />
                    <select name="type" onChange={this.onChangeHandler}>
                        <option value="GENETIC_PREDISPOSITION">GENETIC PREDISPOSITION</option>
                        <option value="NEXT_GENERATION_SEQUENCING">NEXT_GENERATION SEQUENCING</option>
                        <option value="CHROMOSOMAL_ABNORMALITY">CHROMOSOMAL ABNORMALITY</option>
                    </select>
                    <input type="submit" className="btn btn-primary" value="Create" />
                </form>
            </div>
        );
    }
}

export default withRouter(AnalysesAddPage);