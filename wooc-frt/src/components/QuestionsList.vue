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
            <li>Question List</li>
          </ul>
        </div>

        <div class="login">
          <a>Create account</a>
          <a>Log in</a>
        </div>
      </div>
    </div>

    <div class="page-content">
      <div class="side-nav">
        <ul>
          <li :class="{ active: isViewMode }"><a @click="toggleViewMode(true)">View</a></li>
          <li :class="{ active: !isViewMode }"><a @click="toggleViewMode(false)">Edit</a></li>
        </ul>
      </div>

      <div class="container">
        <ul class="list-group">
          <li v-for="question in questions" :key="question.id" class="list-group-item">
            <div>
              <h5>{{ question.title }}</h5>
              <ol>
                <li>{{ question.optionA }}</li>
                <li>{{ question.optionB }}</li>
                <li>{{ question.optionC }}</li>
                <li>{{ question.optionD }}</li>
              </ol>
              <div v-if="!isViewMode">
                Correct Answer: {{ correctAnswerText(question) }}
              </div>
            </div>
            <div class="edit" v-if="!isViewMode">
              <router-link :to="'/question/edit/' + question.id" class="btn btn-primary btn-sm">Edit</router-link>
              <button @click="deleteQuestion(question.id)" class="btn btn-danger btn-sm">Delete</button>
            </div>
          </li>
        </ul>
        <div class="newquestion" v-if="!isViewMode">
          <router-link to="/question/add" class="btn btn-add">New Question</router-link>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import axios from 'axios';

export default {
  name: 'QuestionsList',
  data() {
    return {
      questions: [],
      isViewMode: false, 
    };
  },
  created() {
    this.fetchQuestions();
  },
  methods: {
    fetchQuestions() {
      axios.get('http://localhost:8080/api/v1/questions')
        .then(response => {
          this.questions = response.data;
        })
        .catch(error => {
          console.error('Failed when loading question list:', error);
        });
    },
    deleteQuestion(id) {
      axios.delete(`http://localhost:8080/api/v1/questions/${id}`)
        .then(() => {
          this.fetchQuestions(); 
        })
        .catch(error => {
          console.error('Failed when deleting this question:', error);
        });
    },
    toggleViewMode(isView) {
      this.isViewMode = isView;
    }
  },
  computed: {
    correctAnswerText() {
      return function(question) {
        switch(question.correctAnswer) {
          case 1: return question.optionA;
          case 2: return question.optionB;
          case 3: return question.optionC;
          case 4: return question.optionD;
          default: return '';
        }
      }
    }
  }
};
</script>
