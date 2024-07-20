import { defineStore } from 'pinia';

export const usePeopleStore = defineStore('people', {
  state: () => ({
    people: {}
  }),
  actions: {
    setPeople(data) {
      this.people = data;
    }
  }
});
