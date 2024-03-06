<template>
  <div>
    <div class="header">
      <div class="header-container">
        <div class="menu">
          <ul>
            <li><a>Wooclap</a></li>
          </ul>
        </div>
        <div class="list">
          <ul>
            <li>{{ isEditing ? 'Edit Questions' : 'Add Questions' }}</li>
          </ul>
        </div>
        <div class="login">
          <a>Create account</a>
          <a>Log in</a>
        </div>
      </div>
    </div>
    <div class="page-content">
      <div class="list-group-item-form">
        <form @submit.prevent="submitForm">
          <div class="list-group-form">
            <label for="title">Question:</label>
            <input id="title" v-model="question.title" type="text" required>
          </div>
  
          <div v-for="(option, index) in question.options" :key="index" class=" list-group-option">
            <label :for="'answer-' + index">Option {{ option.letter }}:</label>
            <input :id="'answer-' + index" v-model="option.text" type="text" required>
          </div>

          <div>
            <label for="correctAnswer">Correct Answer:</label>
            <select id="correctAnswer" v-model="question.correctAnswer">
              <option v-for="n in 4" :key="n" :value="n">{{ n }}</option>
            </select>
          </div>
  
          <button type="submit">Submit</button>
        </form>
      </div>
    </div>
  </div>
</template>
  
<script>
  import axios from 'axios';
  
  export default {
    name: 'QuestionForm',
    data() {
      return {
        question: {
          title: '',
          options: [
            { text: '', letter: 'A' },
            { text: '', letter: 'B' },
            { text: '', letter: 'C' },
            { text: '', letter: 'D' }
          ],
          correctAnswer: 1,
        },
        isEditing: false,
      };
    },
    methods: {
      submitForm() {
        const url = this.isEditing ? `http://localhost:8080/api/v1/questions/${this.$route.params.id}` : 'http://localhost:8080/api/v1/questions';
        const payload = {
          title: this.question.title,
          optionA: this.question.options[0].text,
          optionB: this.question.options[1].text,
          optionC: this.question.options[2].text,
          optionD: this.question.options[3].text,
          correctAnswer: this.question.correctAnswer,
        };
        axios.post(url, payload)
          .then(() => {
            this.$router.push('/');
          })
          .catch(error => {
            console.error('Failed when submitting:', error);
          });
      }
    }
  };
  </script>
  