import React, { Component } from 'react';
import { getAnalysesDetails } from '../../api/remote';

export default class DetailsPage extends Component {

    constructor(props) {
        super(props);

        this.state = {
            analyses: false,
            error: false
        };
    }

    componentDidMount() {
        this.getData();
    }

    async getData() {
        const res = await getAnalysesDetails(this.props.match.params.id);
        this.setState({ analyses: res });
    }


    render() {
        let main = <p>Loading &hellip;</p>;
        if (this.state.analyses) {
            const analyses = this.state.analyses;
            const picName = '/' + analyses.name + '.png';
            main = (
                <div class="card" width="18rem">
                    <div class="card-header">
                        <h4>{analyses.name}</h4>
                    </div>
                    <div class="card-body">
                        <div class="card-text">{analyses.description}</div>
                    </div>
                    <div class="card-body">
                        <div class="card-text"><h5>Type</h5> {analyses.type}</div>
                    </div>
                    <div class="card-body">
                        <p class="card-text"><h5>Price</h5> {analyses.price} lv</p>
                    </div>
                    <div class="card-body">

                        <p class="card-text"><h5>TAT</h5> {analyses.periodOfProduct} days</p>
                    </div>
                </div>
            );
        }

        return (
            <div className="container">
                {main}
            </div>
        );
    }
}