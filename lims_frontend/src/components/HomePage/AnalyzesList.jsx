import React, { Component } from 'react';
import AnalysesCard from './AnalysesCard';

export default class AnalyzesList extends Component {
    render() {
        return (
            <div>
                {this.props.analyzes.map(t => (
                    <AnalysesCard
                        key={t.id}
                        id={t.id}
                        type={t.type}
                        price={t.price}
                        name={t.name}/>
                ))}
            </div>
        );
    }
}