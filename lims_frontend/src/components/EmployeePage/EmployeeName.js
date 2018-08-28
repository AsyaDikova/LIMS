import React, { Component } from 'react';

export default class EmployeeName extends Component {
    render() {
        return (
            <select>
                {this.props.empoyees.map(em => (
                    <option value={em}> {em} </option>
                ))}
            </select>
        );
    }
}