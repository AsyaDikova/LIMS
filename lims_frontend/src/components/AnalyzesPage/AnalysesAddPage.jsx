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
            return;
        } else {
            NotificationManager.success(res.message);
        }
        this.props.history.push('/');
    }

    render() {

        return (
            <div className="container">
                <h1>Create Analyses</h1>
                <form onSubmit={this.onSubmitHandler}>
                    <Input
                        name="name"
                        value={this.state.name}
                        type="text"
                        onChange={this.onChangeHandler}
                        label="Name"
                    />
                    <div class="form-group">
                        <label htmlFor="price">Price</label>
                        <input
                            class="form-control"
                            name="price"
                            value={this.state.price}
                            type="number"
                            step="0.01"
                            onChange={this.onChangeHandler}
                        />
                    </div>

                    <div class="form-group">
                        <label for="description">Description</label>
                        <textarea class="form-control" rows="5" id="description"
                                  name="description"
                                  type="text"
                                  value={this.state.description}
                                  onChange={this.onChangeHandler}>

                        </textarea>
                    </div>
                    {/*<Input*/}
                        {/*name="description"*/}
                        {/*type="text"*/}
                        {/*value={this.state.description}*/}
                        {/*onChange={this.onChangeHandler}*/}
                        {/*label="Description"*/}
                    {/*/>*/}
                    <Input
                        name="periodOfProduct"
                        type="number"
                        step="1"
                        value={this.state.periodOfProduct}
                        onChange={this.onChangeHandler}
                        label="Days of analyses"
                    />
                    <div class="form-group">
                        <label for="exampleSelect1">Analysis Types</label>
                            <select name="type" class="form-control" onChange={this.onChangeHandler}>
                                <option value="GENETIC_PREDISPOSITION">GENETIC PREDISPOSITION</option>
                                <option value="NEXT_GENERATION_SEQUENCING">NEXT_GENERATION SEQUENCING</option>
                                <option value="CHROMOSOMAL_ABNORMALITY">CHROMOSOMAL ABNORMALITY</option>
                            </select>
                    </div>
                    <input type="submit" className="btn btn-primary" value="Create" />
                </form>
            </div>
        );
    }
}

export default withRouter(AnalysesAddPage);