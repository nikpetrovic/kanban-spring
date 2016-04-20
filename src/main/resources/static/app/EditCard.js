import React, { Component } from 'react';
import CardForm from './CardForm';

class EditCard extends Component {
  componentWillMount() {
  	let card = this.props.cards.find((card) => card.id == this.props.params.card_id);
  	this.setState(...card);
  }

  handleChange(field, value) {
  	this.setState({[field]: value});
  }

  handleSubmit(e) {
  	e.preventDefault();
  	this.props.cardCallback.updateCard(this.state);
  	browserHistory.push('/');
  }

  hanldeClose(e) {
	browserHistory.push('/');
  }

  render() {
    return (
      <CardForm draftCard={this.state}
      	buttonLabel="Edit Card"
      	handleChange={this.handleChange.bind(this)}
      	handleSubmit={this.handleSubmit.bind(this)}
      	hanldeClose={this.hanldeClose.bind(this)} />
    );
  }
}

export default EditCard;