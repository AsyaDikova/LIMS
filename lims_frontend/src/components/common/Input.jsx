import React, { Component } from 'react';

export default class Input extends Component {
    render() {
        const { name, type = 'text', value, onChange, label } = this.props;
        return (

            <div class="form-group">
                <label htmlFor={label}>{label}</label>
                <input
                    class="form-control"
                    onChange={onChange}
                    name={name}
                    id={name}
                    type={type}
                    value={value}
                    required
                />
            </div>
        );
    }
}