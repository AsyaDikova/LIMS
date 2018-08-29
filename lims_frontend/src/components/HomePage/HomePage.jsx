import React, { Component } from 'react';
import { getAnalyzes } from '../../api/remote';
import AnalyzesList from './AnalyzesList';

export default class HomePage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            analyzes: []
        };
    }

    componentDidMount() {
        this.getData();
    }


    async getData() {
        const data = await getAnalyzes();
        this.setState({ analyzes: data.analyzes });
    }


    render() {

        return (
            <div className="container">
                <img src="https://png.pngtree.com/element_origin_min_pic/17/02/19/9873471d38895b0d47ab73f8faab47d1.jpg" height="80" width="100%"/>
                <h2>Our Analysis</h2>
                {this.state.analyzes.length === 0 ?
                    <p>Loading &hellip;</p> :
                    <AnalyzesList analyzes={this.state.analyzes}  />}
            </div>
        );
    }
}