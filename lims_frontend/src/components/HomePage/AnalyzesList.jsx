import React, { Component } from 'react';
import AnalysesCard from './AnalysesCard';

export default class AnalyzesList extends Component {
    render() {
        return (
            <div>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Analysis Name</th>
                        <th scope="col">Price</th>
                        <th scope="col">Type</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.props.analyzes.map(t => (
                        <AnalysesCard
                            key={t.id}
                            id={t.id}
                            type={t.type}
                            price={t.price}
                            name={t.name}/>
                    ))}
                    </tbody>
                </table>
            </div>
        );
    }
}