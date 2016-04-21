import React, { Component } from 'react';
import CardForm from './CardForm';
import { browserHistory } from 'react-router';

class EditCard extends Component {
  componentWillMount() {
  	let card = this.props.cards.find((card) => card.id == this.props.params.card_id);
  	this.setState(card);
  }

  handleChange(field, value) {
  	this.setState({[field]: value});
  }

  handleSubmit(e) {
  	e.preventDefault();
  	this.props.cardCallbacks.updateCard(this.state);
  	browserHistory.push('/');
  }

  handleClose(e) {
	browserHistory.push('/');
  }

  render() {
    return (
      <CardForm draftCard={this.state}
      	buttonLabel="Edit Card"
      	handleChange={this.handleChange.bind(this)}
      	handleSubmit={this.handleSubmit.bind(this)}
      	handleClose={this.handleClose.bind(this)} />
    );
  }
}

export default EditCard;