import { defineStore } from 'pinia';

export const useLoadingStore = defineStore('loading', {
  state: () => ({
    isLoading: true,
  }),
  actions: {
    setLoading(value: boolean) {
      this.isLoading = value;
    },
  },
});
